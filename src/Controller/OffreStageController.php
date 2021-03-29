<?php

namespace App\Controller;

use App\Entity\OffreStage;
use App\Form\OffreStageType;
use App\Repository\OffreStageRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/offre/stage")
 */
class OffreStageController extends AbstractController
{
    /**
     * @Route("/", name="offre_stage_index", methods={"GET"})
     */
    public function index(OffreStageRepository $offreStageRepository): Response
    {
        return $this->render('offre_stage/index.html.twig', [
            'offre_stages' => $offreStageRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="offre_stage_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $offreStage = new OffreStage();
        $form = $this->createForm(OffreStageType::class, $offreStage);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($offreStage);
            $entityManager->flush();

            return $this->redirectToRoute('offre_stage_index');
        }

        return $this->render('offre_stage/new.html.twig', [
            'offre_stage' => $offreStage,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="offre_stage_show", methods={"GET"})
     */
    public function show(OffreStage $offreStage): Response
    {
        return $this->render('offre_stage/show.html.twig', [
            'offre_stage' => $offreStage,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="offre_stage_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, OffreStage $offreStage): Response
    {
        $form = $this->createForm(OffreStageType::class, $offreStage);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('offre_stage_index');
        }

        return $this->render('offre_stage/edit.html.twig', [
            'offre_stage' => $offreStage,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="offre_stage_delete", methods={"DELETE"})
     */
    public function delete(Request $request, OffreStage $offreStage): Response
    {
        if ($this->isCsrfTokenValid('delete'.$offreStage->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($offreStage);
            $entityManager->flush();
        }

        return $this->redirectToRoute('offre_stage_index');
    }
}
