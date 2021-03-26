<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class DemandeCertificationController extends AbstractController
{
    /**
     * @Route("/demande_certification", name="demande_certification")
     */
    public function index(): Response
    {
        return $this->render('demande_certification/index.html.twig', [
            'controller_name' => 'DemandeCertificationController',
        ]);
    }
}
