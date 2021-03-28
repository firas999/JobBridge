<?php

namespace App\Controller;

use App\Entity\Rec2;
use App\Entity\Employee;

use App\Form\Rec2Type;
use App\Repository\Rec2Repository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/rec2")
 */
class Rec2Controller extends AbstractController
{
    /**
     * @Route("/index/{iduser}", name="rec2_index", methods={"GET"})
     */
    public function index(Rec2Repository $rec2Repository): Response
    {
       ;

        return $this->render('rec2/index.html.twig', [
            'rec2s' => $rec2Repository->findAll()
        ]);

    }
    /**
     * @Route("/indexuser/{iduser}", name="rec2_indexuser", methods={"GET"})
     */
    public function indexuser(Rec2Repository $rec2Repository,$iduser): Response
    {
        $employee=$this->getDoctrine()->getRepository(Employee::class)->find($iduser);

        return $this->render('rec2/indexuser.html.twig', [
            'rec2s' => $employee->getRec2s(),
        ]);

    }


    /**
     * @Route("/new/{iduser}", name="rec2_new", methods={"GET","POST"})
     */
    public function new(Request $request, $iduser): Response
    {
        $employee=$this->getDoctrine()->getRepository(Employee::class)->find($iduser);
        $rec2 = new Rec2();
        $rec2->setEmployee($employee);
        $form = $this->createForm(Rec2Type::class, $rec2);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($rec2);
            $entityManager->flush();

            return $this->redirectToRoute('rec2_index', ['iduser' => $iduser]);
        }

        return $this->render('rec2/new.html.twig', [
            'rec2' => $rec2,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/newuser/{iduser}", name="rec2_newuser", methods={"GET","POST"})
     */
    public function newuser(Request $request, $iduser): Response
    {
        $employee=$this->getDoctrine()->getRepository(Employee::class)->find($iduser);
        $rec2 = new Rec2();
        $rec2->setEmployee($employee);
        $form = $this->createForm(Rec2Type::class, $rec2);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($rec2);
            $entityManager->flush();

            return $this->redirectToRoute('rec2_indexuser', ['iduser' => $iduser]);
        }

        return $this->render('rec2/new.html.twig', [
            'rec2' => $rec2,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="rec2_show", methods={"GET"})
     */
    public function show(Rec2 $rec2): Response
    {

        return $this->render('rec2/show.html.twig', [
            'rec2' => $rec2,
        ]);
    }

    /**
     * @Route("/{id}/{iduser}/edit", name="rec2_edit", methods={"GET","POST","DELETE"})
     */
    public function edit(Request $request, Rec2 $rec2,$iduser): Response
    {
        $employee=$this->getDoctrine()->getRepository(Employee::class)->find($iduser);

        $rec2->setEmployee($employee);

        $form = $this->createForm(Rec2Type::class, $rec2);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

             return $this->redirectToRoute('rec2_index', ['iduser' => $iduser]);
        }

        return $this->render('rec2/edit.html.twig', [
            'rec2' => $rec2,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}/{iduser}", name="rec2_delete", methods={"DELETE"})
     */
    public function delete(Request $request, Rec2 $rec2 , $iduser): Response
    {
        if ($this->isCsrfTokenValid('delete'.$rec2->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $employee = $this->getDoctrine()->getRepository(Employee::class)->find($iduser);
            $employee->removeRec2($rec2);
            $entityManager->remove($rec2);
            $entityManager->flush();
        }

        return $this->redirectToRoute('rec2_index', ['iduser' => $iduser]);
    }
    /**
     * @Route("/delete/{id}/{iduser}", name="rec2_deleteuser", methods={"DELETE"})
     */
    public function deleteuser(Request $request, Rec2 $rec2 , $iduser): Response
    {
        if ($this->isCsrfTokenValid('delete'.$rec2->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $employee = $this->getDoctrine()->getRepository(Employee::class)->find($iduser);
            $employee->removeRec2($rec2);
            $entityManager->remove($rec2);
            $entityManager->flush();
        }

        return $this->redirectToRoute('rec2_indexuser', ['iduser' => $iduser]);
    }


}